package com.epam.pharmacy.tag;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Role;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AccessTagControl extends TagSupport {

    private String accessName;

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        if (account == null) {
            return SKIP_BODY;
        }

        Role role = account.getRole();

        if (role.hasAccess(accessName)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
