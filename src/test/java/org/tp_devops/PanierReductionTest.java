package org.tp_devops;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PanierReductionTest {

    @ParameterizedTest
    @CsvSource({
            " , 100.0",
            "REDUC10, 90.0",
            "REDUC20, 80.0"
    })
    void calculerTotalDoitAppliquerLaBonneReduction(
            String code, double totalAttendu) {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 10);

        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            " , 22.50",
            "REDUC10, 20.25",
            "REDUC20, 18.0"
    })
    void calculerTotalAvecPlusieursArticlesDoitAppliquerLaBonneReduction(
            String code, double totalAttendu) {
        Panier panier = new Panier();
        Article a1 = new Article("REF-001", "Stylo", 1.50);
        Article a2 = new Article("REF-002", "Cahier", 3.00);
        panier.ajouterArticle(a1, 5);  // 7.50
        panier.ajouterArticle(a2, 5);  // 15.00
        // sous-total = 22.50

        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            ", Règle, 1.0, 0",
            "REF-001, Règle, 1.0, -3",
            ", Règle, -1.0, 1"
    })
    void casInvalidesDoiventLeverException(
            String reference, String nom, double prix, int quantite) {
        Panier panier = new Panier();
        assertThrows(IllegalArgumentException.class, () -> {
            Article article = new Article(
                    (reference != null && !reference.isBlank()) ? reference.trim() : null,
                    nom,
                    prix);
            panier.ajouterArticle(article, quantite);
        });
    }
}
