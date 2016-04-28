/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package lab9.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SimpleAdhocReport {

    String[] cols;
    ArrayList<ArrayList<String>> values;

    public SimpleAdhocReport(String[] tcols, ArrayList<ArrayList<String>> tvalues) {
        this.cols = tcols;
        this.values = tvalues;
        build();
    }

    private void build() {
        AdhocConfiguration configuration = new AdhocConfiguration();
        AdhocReport report = new AdhocReport();
        configuration.setReport(report);

        for (int i = 0; i < cols.length; i++) {
            AdhocColumn column = new AdhocColumn();
            column.setName(cols[i]);
            report.addColumn(column);
        }

        try {
            //The following code stores the configuration to an xml file
            AdhocManager.saveConfiguration(configuration, new FileOutputStream("configuration.xml"));
            @SuppressWarnings("unused")
            //The following code loads a configuration from an xml file
            AdhocConfiguration loadedConfiguration = AdhocManager.loadConfiguration(new FileInputStream("configuration.xml"));

            JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport());
            reportBuilder.setDataSource(createDataSource());
            reportBuilder.show();
        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("nr_matricol", "nume", "prenume");
        for (int i = 0; i < values.size(); i++) {
            dataSource.add(values.get(i).get(0), values.get(i).get(1), values.get(i).get(2));
//            for (int j = 0; j< values.get(i).size(); j++){
//                                dataSource.add(values.get(i).get(0), values.get(i).get(1), values.get(i).get(2));
//            }
        }
        return dataSource;
    }
}
