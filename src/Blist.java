public class Blist {
    // Çift yönlü bağlı listenin baş ve son referansları
    blnode head;
    blnode end;

    // Listeyi başlatan kurucu metot
    public Blist() {
        head = null;
        end = null;
    }

    // Listenin düğümünü temsil eden iç sınıf
    public static class blnode {
        Node urun; // Düğümde saklanan ürün
        blnode next; // Sonraki düğüme referans
        blnode back; // Önceki düğüme referans
    }

    // Ağaca çocuk düğüm ekleme metodu
    public void ekle(String eleman, Blist eklenecekCocuklar) {
        Node yeniNode = new Node(eleman);
        blnode yeniBlnode = new blnode();
        yeniBlnode.urun = yeniNode;

        if (eklenecekCocuklar.head == null) {
            // Eğer çocuk listesi boşsa, yeni düğümü baş olarak ayarla
            eklenecekCocuklar.head = yeniBlnode;
        } else if (eklenecekCocuklar.end == null) {
            // Liste yalnızca bir elemana sahipse, yeni düğümü sona ekle
            eklenecekCocuklar.end = yeniBlnode;
            eklenecekCocuklar.head.next = eklenecekCocuklar.end;
            eklenecekCocuklar.end.back = eklenecekCocuklar.head;
        } else {
            // Yeni düğümü listenin sonuna ekle
            yeniBlnode.back = eklenecekCocuklar.end;
            eklenecekCocuklar.end.next = yeniBlnode;
            eklenecekCocuklar.end = yeniBlnode;
        }
    }

    // Ürün adına göre kardeşler arasında arama yapma metodu
    public Node ara(String urunAdi) {
        blnode current = head;
        while (current != null) {
            if (current.urun.name.equals(urunAdi)) {
                return current.urun; // Ürün bulundu
            }
            current = current.next; // Sonraki düğüme geç
        }
        return null; // Ürün bulunamadı
    }

    // Ürün düğümünü listeden silme metodu
    public void sil(Node urun) {
        blnode current = head;

        while (current != null) {
            if (current.urun == urun) {
                // Sonraki düğümün önceki referansını güncelle
                if (current.back != null) {
                    current.back.next = current.next;
                } else {
                    head = current.next; // Silinen ilk eleman ise başı güncelle
                }

                // Önceki düğümün sonraki referansını güncelle
                if (current.next != null) {
                    current.next.back = current.back;
                } else {
                    end = current.back; // Silinen son eleman ise sonu güncelle
                }

                current.next = null;
                current.back = null;
                return; // Düğüm silindiği için metottan çık
            }
            current = current.next;
        }
    }

    // Listeyi dolaşarak toplam ürün miktarını sayma metodu
    public int gez() {
        blnode geciciNode = head;
        int toplamAdet = 0;
        while (geciciNode != null) {
            toplamAdet += geciciNode.urun.adet;
            geciciNode = geciciNode.next;
        }
        return toplamAdet;
    }
}
