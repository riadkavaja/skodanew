SELECT SUM(ProduktiShitur.Sasia*Produktet.Cmim_dalje) as Cmimi, ProduktiShitur.ID_Fatura  as faturaid
            FROM ProduktiShitur  
            INNER JOIN Produktet 
            ON ProduktiShitur.ID_Prod = Produktet.ID_Produktet 
            GROUP BY faturaid ORDER BY Cmimi DESC;