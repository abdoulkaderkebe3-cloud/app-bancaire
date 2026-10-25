dans nos services on utilise notre repository pour communiquer avec la base de donnees,
notre validateur aussi pour les exception;
ex: private final UserRepository repository;
    private final ObjectValidator <un type generique> validator;

dans la methode save il fau valider l'objet avant de le persister