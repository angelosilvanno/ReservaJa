public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String senha;

    public Cliente(int id, String nome, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
    }
    
}