import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        DecimalFormat salarioFormatado = new DecimalFormat("#,##0.00");
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.88"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995,1,5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,3,31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994,7,8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,5,24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996,9,2), new BigDecimal("2799.93"), "Gerente"));

        //Removendo o funcionário João.
        funcionarios.removeIf((funcionario) -> funcionario.getNome().equals("João"));

        //Exibindo os funcionários e suas innformações
        funcionarios.forEach(System.out::println);

        //Aumentando o salário em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.1"));
            funcionario.setSalario(novoSalario);
        });

        //Agrupando todos os funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect
                (Collectors.groupingBy(Funcionario::getFuncao));

        //Imprimindo os funcionários que foram agrupados por função
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("\nFunção: " + funcao);
            listaFuncionarios.forEach(System.out::println);
        });

        //Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        funcionarios.stream()
                .filter(funcio -> funcio.getDataNascimento().getMonthValue() == 10 || funcio.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        //Imprimir o funcionário com maior idade
        Funcionario maisVelho = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElse(null);
        System.out.println("\n\nFuncionário mais velho:\nNome: " + maisVelho.getNome() + "\nIdade: " + maisVelho.getIdade());

        //Imprimindo a lista de funcionário em ordem alfabpética
        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(System.out::println);

        //Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n\nSalário Total: R$ " + salarioFormatado.format(totalSalarios));

        //Imprindo quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario ->{
            BigDecimal qtdSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("\nNome: " + funcionario.getNome() + "\nSalários Mínimos: " + qtdSalariosMinimos);
        });
    }
}