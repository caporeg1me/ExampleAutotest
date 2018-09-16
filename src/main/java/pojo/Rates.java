package pojo;

public class Rates {

    private Currency usd;
    private Currency eur;
    private Currency brent;
    private int intouch;
    private String btc;

    public Rates(Currency usd, Currency eur, Currency brent, int intouch, String btc) {
        this.usd = usd;
        this.eur = eur;
        this.brent = brent;
        this.intouch = intouch;
        this.btc = btc;
    }

    public Rates() {

    }

    public Currency getUsd() {
        return usd;
    }

    public void setUsd(Currency usd) {
        this.usd = usd;
    }

    public Currency getEur() {
        return eur;
    }

    public void setEur(Currency eur) {
        this.eur = eur;
    }

    public Currency getBrent() {
        return brent;
    }

    public void setBrent(Currency brent) {
        this.brent = brent;
    }

    public int getIntouch() {
        return intouch;
    }

    public void setIntouch(int intouch) {
        this.intouch = intouch;
    }

    public String getBtc() {
        return btc;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }
}
