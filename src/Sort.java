public class Sort {
    // Alfabe dizisi (Türkçe karakterler dahil)
    private static final char[] ALFABE = {'\u0000', 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş',
            't', 'u', 'ü', 'v', 'y', 'z'};

    // Bir harfin alfabe sırasını bulan metot
    public static int harfSirasi(char karakter) {
        for (int i = 0; i < ALFABE.length; i++) {
            if (ALFABE[i] == karakter) {
                return i;
            }
        }
        return -1; // Harf bulunamazsa -1 döndür
    }

    // İki kelimeyi Türkçe alfabeye göre karşılaştırma metodu
    public static int karsilastirma(String ilk, String ikinci) {
        // İki kelimenin en kısa uzunluğunu bul
        int kisaUzunluk = Math.min(ilk.length(), ikinci.length());

        for (int n = 0; n < kisaUzunluk; n++) {
            // Harf sırasını bulmak için harfSirasi metodunu kullan
            int siraIlk = harfSirasi(ilk.toLowerCase().charAt(n));
            int siraIkinci = harfSirasi(ikinci.toLowerCase().charAt(n));

            if (siraIlk != siraIkinci) {
                // Harf sıraları farklı ise farkı döndür
                return siraIlk - siraIkinci;

                /*
                Pozitif: İlk kelime, ikinci kelimeden sonra gelmeli.
                Negatif: İlk kelime, ikinci kelimeden önce gelmeli.
                Sıfır: Sıralamada değişiklik yapılmaz.
                */
            }
        }

        // Eğer harfler aynı ise, uzunluk farklılığına göre sıralama yap
        return ilk.length() - ikinci.length();
    }

    // Diziyi sıralayan metot (Bubble Sort algoritması)
    public static String[] sirala(String[] liste) {
        for (int i = 0; i < liste.length - 1; i++) {
            for (int j = 0; j < liste.length - i - 1; j++) {
                // İki kelimeyi karşılaştır ve yer değiştir gerekiyorsa
                if (karsilastirma(liste[j], liste[j + 1]) > 0) {
                    // Pozitif ise yer değiştir
                    String temp = liste[j];
                    liste[j] = liste[j + 1];
                    liste[j + 1] = temp;
                }
            }
        }
        return liste; // Sıralanmış listeyi döndür
    }
}
