function verificaLogin(){
	var usuario = $("#usuario").val();
	var senha = $("#senha").val();
	var erros = new Array(); 
	erros['usuario'] = erros['senha']  = "";
	if(usuario == "" || usuario.length < 4){
		erros['usuario'] = "Usuario deve ter no minimo 4 caracteres.";
	}
	if(senha == "" || senha.length < 6 || senha.length > 12){
		erros['senha'] = "Senha deve ter entre 6 e 12 caracteres.";
	}
	$("#usuario + .text-danger").text(erros['usuario']);
	$("#senha + .text-danger").text(erros['senha']);
	if(erros['usuario'] == "" && erros['senha'] == ""){
		$("#form-login").submit();
	}
}

function alterarSenha(){
	var senhaAtual = $("#senhaAtual").val();
	var senhaNova  = $("#senhaNova").val();
	var senhaConf  = $("#senhaConf").val();
	var erros = new Array(); 
	erros['satual'] = erros['snova'] = erros['sconf'] = "";
	if(senhaAtual == "" || senhaAtual.length < 6 || senhaAtual.length > 12){
		erros['satual'] = "Senha atual deve ter entre 6 e 12 caracteres.";
	}
	if(senhaNova == senhaAtual){
		erros['snova'] = "Senha nova e senha atual devem ser dieferentes.";
	}
	if(senhaNova == "" || senhaNova.length < 6 || senhaNova.length > 12){
		erros['snova'] = "Senha nova deve ter entre 6 e 12 caracteres.";
	}
	if(senhaConf != senhaNova){
		erros['sconf'] = "Senha deve ser igual a nova senha.";
	}
	if(senhaConf == "" || senhaConf.length < 6 || senhaConf.length > 12){
		erros['sconf'] = "Senha deve ter entre 6 e 12 caracteres";
	}
	$("#senhaAtual + .text-danger").text(erros['satual']);
	$("#senhaNova + .text-danger").text(erros['snova']);
	$("#senhaConf + .text-danger").text(erros['sconf']);
	if(erros['satual'] == "" && erros['snova'] == ""  && erros['sconf'] == ""){
		$("#form-AlterarSenha").submit();
	}
	
}
 

function resetarSenha(){
	var senhaNova  = $("#senhaNova").val();
	var senhaConf  = $("#senhaConf").val();
	var erros = new Array(); 
        erros['snova'] = erros['sconf'] = "";
	if(senhaNova == "" || senhaNova.length < 6 || senhaNova.length > 12){
		erros['snova'] = "Senha nova deve ter entre 6 e 12 caracteres.";
	}
	if(senhaConf != senhaNova){
		erros['sconf'] = "Senha deve ser igual a nova senha.";
	}
	if(senhaConf == "" || senhaConf.length < 6 || senhaConf.length > 12){
		erros['sconf'] = "Senha deve ter entre 6 e 12 caracteres";
	}
	$("#senhaNova + .text-danger").text(erros['snova']);
	$("#senhaConf + .text-danger").text(erros['sconf']);
	if(erros['snova'] == ""  && erros['sconf'] == ""){
		$("#form-AlterarSenha").submit();
	}
	
}

