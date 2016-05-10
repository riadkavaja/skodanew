SELECT SUM(ProduktiShitur.Sasia) as Sassia,
                    Produktet.Emri as value
                    FROM ProduktiShitur 
                    INNER JOIN Produktet ON ProduktiShitur.ID_Prod = Produktet.ID_Produktet
                    INNER JOIN Fatura ON ProduktiShitur.ID_Fatura = Fatura.ID_Fatura 
                    WHERE dataOra >= date('now','start of day') 
                    GROUP BY value ORDER BY Sassia DESC;