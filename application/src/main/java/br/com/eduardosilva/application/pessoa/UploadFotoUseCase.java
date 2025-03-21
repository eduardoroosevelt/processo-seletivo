package br.com.eduardosilva.application.pessoa;

import br.com.eduardosilva.application.UseCase;
import br.com.eduardosilva.domain.shared.Resource;

import java.util.List;
import java.util.Set;

public abstract class UploadFotoUseCase extends UseCase<UploadFotoUseCase.Input,UploadFotoUseCase.Output> {
    public interface Input{
        Long pesId();
        Set<Resource> fotos();
    }

    public interface Output{
        List<String> links();
    }
}
