package com.action;

import com.model.Account;
import com.model.Category;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends BaseAction<Account>  {

    public String query(){
        jsonList = accountService.query();
        return "jsonList";
    }


}
