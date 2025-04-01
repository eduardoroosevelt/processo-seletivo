package br.com.eduardosilva.infrastructure.api.controllers;

import br.com.eduardosilva.infrastructure.service.StorageService;
import br.com.eduardosilva.domain.shared.Resource;
import br.com.eduardosilva.infrastructure.util.HashingUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class TesteMinIOController {

    private final StorageService storage;

    public TesteMinIOController(StorageService storage) {
        this.storage = storage;
    }

    @PostMapping
    public ResponseEntity<?> upload(final String id, MultipartFile media){
        storage.store(id ,resourceOf(media));

        return ResponseEntity
                .ok()
                .body("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> get(@PathVariable String id){
        var body = storage.get(id);
        if (body.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o recurso não for encontrado
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, body.get().contentType()) // Definindo o tipo de conteúdo no header
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + body.get().name()) // Definindo o nome do arquivo
                .body(body.get().content());
    }

    @DeleteMapping("")
    public void delete(@RequestParam String id){
        storage.deleteAll(List.of(id));
    }

    @GetMapping("/link-temporario/{id}")
    public String generateTemporaryLink(@PathVariable String id) {
        return storage.generateTemporaryLink(id);
    }

    private Resource resourceOf(final MultipartFile part) {
        if (part == null) {
            return null;
        }

        try {
            return Resource.with(
                    part.getBytes(),
                    HashingUtils.checksum(part.getBytes()),
                    part.getContentType(),
                    part.getOriginalFilename()
            );
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
