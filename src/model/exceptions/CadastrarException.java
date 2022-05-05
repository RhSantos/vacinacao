package model.exceptions;

public class CadastrarException extends RuntimeException {
    public CadastrarException(){
        super();
    }
    public CadastrarException(String msg){
        super(msg);
    }
}
