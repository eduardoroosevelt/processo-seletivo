package br.com.eduardosilva.application;

public abstract class UnitUseCase<IN> {

    public abstract void execute(IN anIn);
}