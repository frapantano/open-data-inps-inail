### 2. `inps/` 

Contiene un file JSON con i dati di esempio delle **pensioni erogate dall’INPS** nel **primo trimestre 2024**, suddivise per **regione, sesso, classe di età, categoria e gestione**.

- **Campi principali (estratti dal JSON)**:
  - `Anno_decorrenza` → anno di riferimento (2024)  
  - `Regione` → regione italiana  
  - `SESSO` → Maschi/Femmine  
  - `Categoria` → tipologia di pensione (`Invalidità`, `Superstiti`, ecc.)  
  - `classe_eta` → classe di età (`Fino a 54`, `55-59`, ecc.)  
  - `Numero_pensioni` → numero di pensioni liquidate nel periodo  
  - `tgestio` → **Gestione previdenziale** di appartenenza (es. FPLD, Gestione Separata, ecc.)

Questi campi permettono di collegare l’analisi INPS alle dinamiche occupazionali e previdenziali.

