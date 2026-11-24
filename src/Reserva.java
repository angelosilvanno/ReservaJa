import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private int idCliente;
    private int numeroMesa;
    private LocalDateTime dataHora;
    private String statusReserva;

    public Reserva(int id, int idCliente, int numeroMesa, LocalDateTime dataHora) {
        this.id = id;
        this.idCliente = idCliente;
        this.numeroMesa = numeroMesa;
        this.dataHora = dataHora;
        this.statusReserva = "Pendente";
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

   public String getStatusReserva() {
        return statusReserva;
    }

    public void confirmarReserva() {
        this.statusReserva = "Confirmada";
    }

    public void cancelarReserva() {
        this.statusReserva = "Cancelada";
    }

    
}