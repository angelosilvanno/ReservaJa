public class Mesa {
    private int numero;
    private int idRestaurante;
    private int capacidade;
    private String status;

    public Mesa(int numero, int idRestaurante, int capacidade, String status) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número da mesa deve ser maior que zero.");
        }
        if (idRestaurante <= 0) {
            throw new IllegalArgumentException("O ID do restaurante deve ser maior que zero.");
        }
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade da mesa deve ser maior que zero.");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("O status da mesa não pode estar vazio.");
        }
        this.numero = numero;
        this.idRestaurante = idRestaurante;
        this.capacidade = capacidade;
        this.status = status.trim();
    }

    public int getNumero() {
        return numero;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("O status da mesa não pode estar vazio.");
        }
        this.status = status.trim();
    }

    public boolean isDisponivel() {
        if (this.status == null) {
            return false;
        }
        return this.status.equalsIgnoreCase("DISPONIVEL") || this.status.equalsIgnoreCase("DISPONÍVEL");
    }
}