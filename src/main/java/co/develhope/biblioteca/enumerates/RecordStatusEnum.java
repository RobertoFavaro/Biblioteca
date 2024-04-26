package co.develhope.biblioteca.enumerates;

public enum RecordStatusEnum {
    A("Attivo"),
    I("Inattivo");
    private String descrizione;

    RecordStatusEnum(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
