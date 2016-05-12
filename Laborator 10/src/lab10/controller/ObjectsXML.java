/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10.controller;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Anca
 */
@XmlRootElement
public class ObjectsXML {
    private ArrayList<ObjectXML> obj=new ArrayList<>();

    public ArrayList<ObjectXML> getObj() {
        return obj;
    }

    public void setObj(ArrayList<ObjectXML> obj) {
        this.obj = obj;
    }
    
    public void addObject(ObjectXML o){
        obj.add(o);
    }
}
