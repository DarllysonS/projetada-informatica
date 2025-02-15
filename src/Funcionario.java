import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa{
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao){
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario(){
        return salario;
    }

    public void setSalario(BigDecimal salario){
        this.salario = salario;
    }

    public String getFuncao(){
        return funcao;
    }

    public void setFuncao(String funcao){
        this.funcao = funcao;
    }

    public void aumentarSalario(BigDecimal percentual){
        this.salario = this.salario.add(this.salario.multiply(percentual));
    }

    public int getIdade(){
        return LocalDate.now().getYear() - getDataNascimento().getYear();
    }

    public String toString(){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return
                "\nNome: " + getNome() +
                "\nData de Nascimento: " + getDataNascimento().format(dataFormatada) +
                "\nSalário: " +
                "\nFunção: " + getFuncao();
    }
}
