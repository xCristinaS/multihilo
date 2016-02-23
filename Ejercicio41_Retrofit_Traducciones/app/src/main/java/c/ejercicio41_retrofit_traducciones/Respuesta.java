package c.ejercicio41_retrofit_traducciones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristina on 23/02/2016.
 */
public class Respuesta {
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("lang")
    @Expose
    public String lang;
    @SerializedName("text")
    @Expose
    public List<String> text = new ArrayList<String>();

    public Respuesta() {
    }

    public Respuesta(Integer code, String lang, List<String> text) {
        this.code = code;
        this.lang = lang;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

}
