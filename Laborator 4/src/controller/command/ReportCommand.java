/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Song;

public class ReportCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException, FileNotFoundException, TemplateException, ClassNotFoundException {
        //Freemarker configuration object
        Configuration cfg = new Configuration();

        //Load template from source folder
        Template template = cfg.getTemplate("src/favorites.ftl");

        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", "Ionut");

        //Deserialization
        Song[] songSer = null;

        FileInputStream fileIn = new FileInputStream("/favorites.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        songSer = (Song[]) in.readObject();
        in.close();
        fileIn.close();

        //List parsing 
        List<String> songs = new ArrayList<String>();
        for (Song s : songSer) {
            songs.add(s.getSongName());
        }

        data.put("songs", songs);

        // Console output
//            Writer out = new OutputStreamWriter(System.out);
//            template.process(data, out);
//            out.flush();
        // File output
        Writer file = new FileWriter(new File(System.getProperty("user.dir") + "\\report.html"));
        template.process(data, file);
        file.flush();
        file.close();
        System.out.println("Report file report.html created sucessfully..");
    }

}
