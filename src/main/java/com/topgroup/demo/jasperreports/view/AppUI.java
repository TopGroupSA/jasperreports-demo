package com.topgroup.demo.jasperreports.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.topgroup.demo.jasperreports.business.service.ProductoService;
import com.topgroup.demo.jasperreports.business.util.JasperReportExecutor;
import com.topgroup.demo.jasperreports.domain.model.Cliente;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class AppUI extends UI {

	private static final long serialVersionUID = 1L;

	private static final String EXPORT_PDF = "PDF";

	private static final String EXPORT_WORD = "Word";

	@Value("${productos.report}")
	private String productosReport;

	@Autowired
	private ProductoService productoService;

	private StreamResource streamResource;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout container = new VerticalLayout();
		container.setMargin(true);
		container.setSpacing(true);
		container.addComponent(new Label("Ejemplo JasperReports"));

		RichTextArea richTextArea = new RichTextArea("Comentarios");
		richTextArea.setImmediate(true);
		container.addComponent(richTextArea);

		OptionGroup optionGroup = new OptionGroup("Elija el formato de salida del reporte");
		optionGroup.addItems(EXPORT_PDF, EXPORT_WORD);
		optionGroup.setValue(EXPORT_PDF);
		optionGroup.setImmediate(true);

		container.addComponent(optionGroup);

		Button descargarButton = new Button("Descargar reporte");
		container.addComponent(descargarButton);

		StreamSource resource = new StreamSource() {

			public InputStream getStream() {
				byte[] data = null;
				JasperReportExecutor executor = new JasperReportExecutor();
				try {
					Cliente cliente = new Cliente();
					cliente.setNumero(2323232);
					cliente.setDenominacion("Compusoft SA");

					executor.compile(productosReport);
					Map<String, Object> params = new HashMap<>();
					params.put("titulo_reporte", "Inventario de productos");
					params.put("cliente", cliente);
					params.put("comentarios", richTextArea.getValue());
					executor.execute(params, new JRBeanCollectionDataSource(productoService.findAll()));
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					switch (optionGroup.getValue().toString()) {
					case EXPORT_PDF:
						streamResource.setFilename("productos_" + System.currentTimeMillis() + ".pdf");
						executor.exportToPdf(bos);
						break;
					case EXPORT_WORD:
						streamResource.setFilename("productos_" + System.currentTimeMillis() + ".doc");
						executor.exportToDoc(bos);
						break;
					}
					data = bos.toByteArray();
				} catch (JRException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				return new ByteArrayInputStream(data);
			}

		};
		streamResource = new StreamResource(resource, "");
		streamResource.setMIMEType("application/unknown");
		streamResource.setCacheTime(0);
		FileDownloader fileDownloader = new FileDownloader(streamResource);
		fileDownloader.extend(descargarButton);
		setContent(container);
	}

}
