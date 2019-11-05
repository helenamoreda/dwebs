package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class S1
 */
@WebServlet(name = "S1", urlPatterns = { "/s1", "/sims" })
public class S1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			createPDF(response, request);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Creates a PDF document.
	 * 
	 * @param response
	 *            Main Response
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createPDF(HttpServletResponse response, HttpServletRequest request)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");

		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		// Texto cabecera
		Paragraph title = new Paragraph("Parametros recibidos del formulario de los sims");
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		// Generaci�n de tabla
		PdfPTable table = new PdfPTable(2); // N�mero de columnas principales
		table.addCell("Parametro");
		table.addCell("Valor");

		// recoger todos los parametros recibidos
		Enumeration<String> names = request.getParameterNames();

		// comprobar que tiene elementos
		if (names.hasMoreElements()) {
			// procesar los parametros
			while (names.hasMoreElements()) {
				String par = names.nextElement();
				PdfPCell nomParam = new PdfPCell(new Phrase(par));
				nomParam.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(nomParam);
				PdfPCell valor = new PdfPCell(new Phrase(request.getParameter(par)));
				valor.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(valor);
			}
		} else {
			response.getWriter().append("No se han recibido parámetros");
		}
		document.add(table);

		// Cierre del documento
		document.close();
	}

}
