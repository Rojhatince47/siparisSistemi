public class Tree {
    // Root (kök) düğümü
    Node root = new Node("root");

    public Tree() {
        root.adet = 0; // Kök düğümünün sipariş sayısı başlangıçta 0 olarak ayarlanır
    }

    // Sipariş ekleme fonksiyonu
    public void ekle(String... siparis) {
        // Siparişleri alfabetik olarak sırala
        Sort.sirala(siparis);

        Node current = root;

        for (String eleman : siparis) {
            Node mevcutChild = current.child.ara(eleman);

            if (mevcutChild != null) {
                // Eğer düğüm zaten varsa, adet sayısını artır
                mevcutChild.adet++;
                current = mevcutChild;
            } else {
                // Yeni bir düğüm ekle
                current.child.ekle(eleman, current.child);
                Node yeniNode = current.child.ara(eleman);
                yeniNode.parent = current;
                current = yeniNode;
            }
        }
    }

    // Sipariş sorgulama fonksiyonu
    public int sorgu(String... sorgum) {
        // Sorgu elemanlarını sırala
        Sort.sirala(sorgum);
        Node gecici = root;

        for (String eleman : sorgum) {
            Node a = gecici.child.ara(eleman);
            if (a != null) {
                gecici = a;
            }
        }

        int altSiparis = gecici.child.gez();
        int fark = gecici.adet - altSiparis;

        if (fark > 0) {
            return fark;
        }

        if (gecici.child == null) {
            return gecici.adet;
        }

        return 0;
    }

    // Silme işlemini dışarıdan erişimle başlatma
    public void getSil(String... silinecekler) {
        sil(silinecekler);
    }

    // Silme işlemi
    private void sil(String[] siraliDizi) {
        Node current;
        int varMi = sorgu(siraliDizi);

        //Eğer sorgulama fonksiyonunda böyle bir sipariş var çıkarsa silme işlemi yapılır
        if (varMi > 0) {
            System.out.println("Silmek istediğiniz sipariş bulundu. Silme işlemi başlatılıyor...");

            current = root;
            for (String eleman : siraliDizi) {
                Node childNode = current.child.ara(eleman);
                if (childNode != null) {
                    childNode.adet--;
                    if (childNode.adet == 0) {
                        current.child.sil(childNode);
                    }
                    current = childNode;
                }
            }

            System.out.println("Silme işlemi tamamlandı.");
        } else {
            System.out.println("Silmek istediğiniz sipariş bulunamadı.");
        }
    }

    // Ağacı yazdırma fonksiyonu
    public void yazdir() {
        yazdirRecursive(root, 0);
    }

    // Ağacı recursive (özyinelemeli) olarak yazdırma
    private void yazdirRecursive(Node dugum, int seviye) {
        for (int i = 0; i < seviye; i++) { // Düğüm kaçıncı seviyede ise ona göre çizgi koyar
            System.out.print("----");
        }
        System.out.println(dugum.name + "  -->  (sipariş sayısı :" + dugum.adet +" )"); // sipariş ve adetlerini yazdırma

        if (dugum.child.head != null) {
            Blist.blnode child = dugum.child.head;
            while (child != null) {
                yazdirRecursive(child.urun, seviye + 1);
                child = child.next;
            }
        }
    }
}