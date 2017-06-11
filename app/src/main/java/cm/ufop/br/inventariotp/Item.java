package cm.ufop.br.inventariotp;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by davidson on 24/05/17.
 */

public class Item implements Serializable {

    public static int ACTION_ADD=1;
    public static int ACTION_EDIT=2;
    public static int ACTION_READ=3;


    private int code;
    private String description, localization, status;
    private Date dateInventory;

    private static Date hoje = new Date();

    public Item(int code, String description, String localization, String status, Date dateInventory) {
        this.code = code;
        this.description = description;
        this.localization = localization;
        this.status = status;
        this.dateInventory = dateInventory;
    }

    public boolean isDelayed() {
        try {
            long diff = (hoje.getTime() - this.dateInventory.getTime())/1000/60/60/24;
            return diff > 365;
        } catch (Exception e) {
            return true;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateInventory() {
        return dateInventory;
    }

    public void setDateInventory(Date dateInventory) {
        this.dateInventory = dateInventory;
    }
}
