import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class administradorTodo extends JFrame{

	private JPanel pPrimero, pListaRanchos, pListaVacas, pBotones, pIntroDatos;
	private JButton bttnAgregarRancho, bttnAgregarVaca, bttnBorrar, bttnSalvar, bttnImportarVacas, bttnClear;
	private JTextField txtNombre, txtPeso, txtMadre, txtPadre, txtSiniga, txtFierro;
	private JLabel lblNombre, lblPeso, lblMadre, lblPadre, lblSiniga, lblFierro;
	private JList lbVacas, lbRanchos;
	private DefaultListModel mVacas, mRanchos;

	public administradorTodo() {
	       inicializarComponentes();
	       cargarDatos();
    }

    private void inicializarComponentes() {
		setSize(550, 550);
		setTitle("Cowmatic");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pListaRanchos = new JPanel(new FlowLayout());
		mRanchos = new DefaultListModel();
		lbRanchos = new JList(mRanchos);
		lbRanchos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				lbRanchosClicked();
			}
		});
		pListaRanchos.add(new JScrollPane(lbRanchos));

	pIntroDatos = new JPanel (new GridLayout(7,3,10,10));

	pIntroDatos.add(new JLabel("Nombre: "));
	txtNombre = new JTextField();
	pIntroDatos.add(txtNombre);

	pIntroDatos.add(new JLabel("Peso: "));
	txtPeso = new JTextField();
	pIntroDatos.add(txtPeso);

	pIntroDatos.add(new JLabel("Madre: "));
	txtMadre = new JTextField();
	pIntroDatos.add(txtMadre);

	pIntroDatos.add(new JLabel("Padre: "));
	txtPadre = new JTextField();
	pIntroDatos.add(txtPadre);

	pIntroDatos.add(new JLabel("Siniga: "));
	txtSiniga = new JTextField();
	pIntroDatos.add(txtSiniga);

	pIntroDatos.add(new JLabel("Fierro: "));
	txtFierro = new JTextField();
	pIntroDatos.add(txtFierro);

	pListaVacas = new JPanel(new FlowLayout());
	mVacas = new DefaultListModel();
	lbVacas = new JList(mVacas);
	lbVacas.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent evt) {
			lbVacasClicked();
		}
	});

	pListaVacas.add(new JScrollPane(lbVacas));

	pBotones = new JPanel();

	bttnAgregarRancho = new JButton("Agregar Rancho");
	bttnAgregarRancho.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			bttnAgregarRanchoClicked();
		}
	});
	pBotones.add(bttnAgregarRancho);

	bttnImportarVacas = new JButton("Importar Vacas");
	bttnImportarVacas.addActionListener(new ActionListener() {
		public void	 actionPerformed(ActionEvent evt) {
			bttnImportarClicked();
		}
	});
	pBotones.add(bttnImportarVacas);

	bttnAgregarVaca = new JButton("Agregar Vaca");
	bttnAgregarVaca.addActionListener(new ActionListener() {
		public void	 actionPerformed(ActionEvent evt) {
			bttnAgregarVacaClicked();
		}
	});
	pBotones.add(bttnAgregarVaca);

	bttnBorrar = new JButton("Borrar");
	bttnBorrar.addActionListener(new ActionListener() {
		public void	 actionPerformed(ActionEvent evt) {
			bttnBorrarClicked();
		}
	});
	pBotones.add(bttnBorrar);

	bttnSalvar = new JButton("Salvar");
	bttnSalvar.addActionListener(new ActionListener() {
		public void	 actionPerformed(ActionEvent evt) {
			bttnSalvarClicked();
		}
	});
	pBotones.add(bttnSalvar);

	bttnClear = new JButton("Clear");
	bttnClear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			borrarFormulario();
		}
	});
	pBotones.add(bttnClear);

	pPrimero = new JPanel(new GridLayout(2,3,5,5));
	pPrimero.add(pListaRanchos);
	pPrimero.add(pIntroDatos);
	pPrimero.add(pListaVacas);
	pPrimero.add(pBotones);

	add(pPrimero);

	setVisible(true);
	}

	private void bttnAgregarRanchoClicked() {
		Rancho r = new Rancho();
		String rancho;
		rancho = JOptionPane.showInputDialog("Introduzca el nombre del rancho: ");
		r.nombre = rancho;
		mRanchos.addElement(r);
		lbRanchos.updateUI();
	}

	private void bttnImportarClicked() {
		//new Lector();
	}

	private void bttnAgregarVacaClicked() {
		Vaca v = new Vaca();
		v.nombre = txtNombre.getText();
		v.peso = Double.parseDouble(txtPeso.getText());
		v.madre = txtMadre.getText();
		v.padre = txtPadre.getText();
		v.siniga = Integer.parseInt(txtSiniga.getText());
		v.fierro = txtFierro.getText();

		mVacas.addElement(v);
		lbVacas.updateUI();
		borrarFormulario();
	}

	private void bttnBorrarClicked() {
		Vaca v = (Vaca)lbVacas.getSelectedValue();

		if (v != null)
            mVacas.removeElement(v);

       Rancho r = (Rancho)lbRanchos.getSelectedValue();
       if (r != null)
       		mRanchos.removeElement(r);
	}

	private void bttnSalvarClicked() {
		try {
			File archivo = new File("Vacas.poo");
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for(int i=0;i<mVacas.getSize();i++) {
					Vaca v = (Vaca)mVacas.get(i);
					oos.writeObject(v);
			}
			oos.close();
		}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Error");
		}

		try {
			File archivoR = new File("Ranchos.poo");
			FileOutputStream fosR = new FileOutputStream(archivoR);
			ObjectOutputStream oosR = new ObjectOutputStream(fosR);

			for(int i=0;i<mRanchos.getSize();i++) {
					Rancho r = (Rancho)mRanchos.get(i);
					oosR.writeObject(r);
			}
			JOptionPane.showMessageDialog(null,"Datos Salvados");
			oosR.close();
		}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Error");
		}

	}

	private void borrarFormulario() {
		txtNombre.setText("");
		txtPeso.setText("");
		txtMadre.setText("");
		txtPadre.setText("");
		txtSiniga.setText("");
		txtFierro.setText("");
	}

	private void lbRanchosClicked() {
		Rancho r = (Rancho)lbRanchos.getSelectedValue();
	}

	private void lbVacasClicked() {
		Vaca v = (Vaca)lbVacas.getSelectedValue();
		if (v != null) {
			txtNombre.setText(v.nombre);
			txtPeso.setText(v.peso + "");
			txtMadre.setText(v.madre);
			txtPadre.setText(v.padre);
			txtSiniga.setText(v.siniga + "");
			txtFierro.setText(v.fierro);
		}
	}

	private void cargarDatos() {
		try{
			File archivo = new File("Vacas.poo");
			FileInputStream fis = new FileInputStream(archivo);

			ObjectInputStream ois = new ObjectInputStream(fis);

			while(true) {
				Vaca v = (Vaca)ois.readObject();
				mVacas.addElement(v);
			}
		}catch(Exception e) {
			lbVacas.updateUI();
		}

		try{
			File archivoR = new File("Ranchos.poo");
			FileInputStream fisR = new FileInputStream(archivoR);

			ObjectInputStream oisR = new ObjectInputStream(fisR);

			while(true) {
				Rancho r = (Rancho)oisR.readObject();
				mRanchos.addElement(r);
			}
		}catch(Exception e) {
			lbRanchos.updateUI();
			JOptionPane.showMessageDialog(null,"Datos Cargados");
		}
	}
}