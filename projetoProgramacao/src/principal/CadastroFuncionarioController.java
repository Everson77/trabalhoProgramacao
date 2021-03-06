package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import principal.dao.AbstractFactory;
import principal.dao.FilialDAO;
import principal.dao.FuncionarioDAO;
import principal.model.Filial;
import principal.model.Funcionario;

public class CadastroFuncionarioController {

	 @FXML
	    private TextField tfNome;

	    @FXML
	    private TextField tfSobrenome;

	    @FXML
	    private DatePicker dtNascimento;

	    @FXML
	    private TextField tfTelefone;

	    @FXML
	    private TextField tfCpf;

	    @FXML
	    private TextField tfEmail;

	    @FXML
	    private TextField tfSalario;
	    
	    @FXML
	    private TextField tfSenha;

	    @FXML
	    private ComboBox<Filial> cbFilial;

	    @FXML
	    private Button btnNovo;

	    @FXML
	    private Button btnCadastro;

	    private Funcionario funcionario;
	    
	    private FuncionarioDAO funcionarioDao = AbstractFactory.get().funcionarioDao();
	    private FilialDAO filialDao = AbstractFactory.get().filialDao();
	    
	    @FXML
		private void initialize() {
			populaCombo();
		}
	    
	    private void populaCombo(){
			for(Filial filial: filialDao.listar()){
				cbFilial.getItems().add(filial);
			}
		}
	    
	    void populaFuncionario() {
	    	funcionario.setNome(tfNome.getText());
	    	funcionario.setSobrenome(tfSobrenome.getText());
	    	funcionario.setDataNascimento(dtNascimento.getValue());
	    	funcionario.setEmail(tfEmail.getText());
	    	funcionario.setCpf(tfCpf.getText());
	    	funcionario.setSenha(tfSenha.getText());
	    	funcionario.setSalario(Double.valueOf(tfSalario.getText()));
	    	funcionario.setTelefone(tfTelefone.getText());
	    }
	    
	    @FXML
	    void cadastrar(ActionEvent event) {
	    	populaFuncionario();
	    	AlertaFactory alerta = new AlertaFactory();
	    	if(alerta.confirmaAceitar()) {
	    		funcionarioDao.inserir(funcionario);
	    	}
	    }

	    @FXML
	    void novo(ActionEvent event) {
	    	novoFuncionario();
	    }
	    
	    void novoFuncionario(){
	    	funcionario = new Funcionario();
	    	tfNome.clear();
	    	tfSobrenome.clear();
	    	dtNascimento.setValue(null);
	    	tfEmail.clear();
	    	tfSenha.clear();
	    	tfSalario.clear();
	    	tfCpf.clear();
	    	tfTelefone.clear();
	    	cbFilial.getSelectionModel().clearSelection();
	    }
}
