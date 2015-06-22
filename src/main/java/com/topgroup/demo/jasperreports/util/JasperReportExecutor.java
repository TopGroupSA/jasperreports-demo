package com.topgroup.demo.jasperreports.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperReportExecutor {

	private JasperReport jasperReport;

	private JasperPrint jasperPrint;

	/**
	 * Compila el reporte de JasperReports
	 * 
	 * @param reportFullPath
	 *            ruta y nombre del reporte en el filesystem.
	 * @return
	 * @throws JRException
	 */
	public final JasperReportExecutor compile(String reportFullPath) throws JRException {
		jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(reportFullPath));
		return this;
	}

	/**
	 * Ejecuta el reporte Jasper con ciertos parametros y datasource.
	 * 
	 * @param parameters
	 *            parametros a pasar al reporte.
	 * @param dataSource
	 *            datasource del reporte.
	 * @return
	 * @throws JRException
	 */
	public JasperReportExecutor execute(Map<String, Object> parameters, JRDataSource dataSource) throws JRException {
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return this;
	}

	/**
	 * Exporta el reporte a PDF en un archivo del filesystem.
	 * 
	 * @param fileName
	 *            nombre del archivo PDF a generar.
	 * @return
	 * @throws JRException
	 * @throws FileNotFoundException
	 */
	public JasperReportExecutor exportToPdf(String fileName) throws JRException, FileNotFoundException {
		return exportToPdf(new FileOutputStream(fileName));
	}

	/**
	 * Exporta el reporte a PDF en un stream.
	 * 
	 * @param os
	 * @return
	 * @throws JRException
	 */
	public JasperReportExecutor exportToPdf(OutputStream os) throws JRException {
		if (jasperPrint != null) {
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.exportReport();
		}
		return this;
	}

	/**
	 * Exporta el reporte a formato Word en un archivo del filesystem.
	 * 
	 * @param fileName
	 *            nombre del archivo PDF a generar.
	 * @return
	 * @throws JRException
	 * @throws FileNotFoundException
	 */
	public JasperReportExecutor exportToDoc(String fileName) throws JRException, FileNotFoundException {
		return exportToDoc(new FileOutputStream(fileName));
	}

	/**
	 * Exporta el reporte a formato Word en un stream.
	 * 
	 * @param os
	 * @return
	 * @throws JRException
	 */
	public JasperReportExecutor exportToDoc(OutputStream os) throws JRException {
		if (jasperPrint != null) {
			JRDocxExporter exporter = new JRDocxExporter();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.exportReport();
		}
		return this;
	}

	/**
	 * Limpia reportes generado.
	 * 
	 * @return
	 */
	public final JasperReportExecutor reset() {
		jasperPrint = null;
		return this;
	}

}