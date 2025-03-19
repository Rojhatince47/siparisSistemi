public class Node {
    // Ürün adı
    String name;
    // Sipariş sayısı (varsayılan olarak 1)
    int adet = 1;
    // Ebeveyn düğümü
    Node parent;
    // Çocukları barındıran bağlantılı liste
    Blist child = new Blist();

    // Yapıcı metot: Ürün adını alır ve ilgili değişkeni atar
    public Node(String urun) {
        this.name = urun;
    }
}