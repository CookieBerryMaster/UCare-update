package org.example.UCare.actions;

import com.openxava.naviox.actions.ForwardToOriginalURIBaseAction;
import com.openxava.naviox.impl.SignInHelper;
import org.example.UCare.model.Estudiantes;
import org.example.UCare.service.IDAO;
import org.example.UCare.service.ImpDAO;
import org.example.UCare.util.Global;
import org.openxava.util.Is;

import java.util.List;

public class ConexionUsuariosAction extends ForwardToOriginalURIBaseAction {
    @Override
    public void execute() throws Exception {
        IDAO dao = new ImpDAO();
        if (getErrors().contains()) return;
        String userName = getView().getValueString("user");
        String password = getView().getValueString("password");
        if (Is.emptyString(userName, password)) {
            addError("unauthorized_user");
            return;
        }


        List lista = dao.get("Estudiantes.findByEstudent", Estudiantes.class,new Object[]{userName,password});
        if(lista ==null || lista.size()==0){
            addError("unauthorized_user");
            return;
        }

        SignInHelper.signIn(getRequest(), userName);
        Global.userName = userName;
        getView().reset();
        getContext().resetAllModulesExceptCurrent(getRequest());
        forwardToOriginalURI();

    }
}
