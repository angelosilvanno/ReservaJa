import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private int numeroMesa;
    private List<ItemMenu> itens;
    private String statusPedido;
    private double valorTotal;

    public Pedido(int id, int numeroMesa) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.itens = new ArrayList<>();
        this.statusPedido = "EM_ANDAMENTO";
        this.valorTotal = 0.0;
    }

    public void adicionarItem(ItemMenu item) {
        if (item != null) {
            this.itens.add(item);
        }
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
        this.valorTotal = total;
        return this.valorTotal;
    }

    public void fecharPedido() {
        calcularTotal();
        this.statusPedido = "FINALIZADO";
    }

    public int getId() {
        return id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<ItemMenu> getItens() {
        return itens;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
}
