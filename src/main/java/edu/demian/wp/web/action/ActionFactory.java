package edu.demian.wp.web.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<>();

    static {
        Action loginAction = new LoginAction();
        Action registerAction = new RegisterAction();
        actions.put("GET/home", new HomeAction());
        actions.put("GET/error", new ErrorAction());
        actions.put("GET/login", loginAction);
        actions.put("POST/login", loginAction);
        actions.put("GET/register", registerAction);
        actions.put("POST/register", registerAction);
    }

    public static Action getAction(HttpServletRequest request) {
        Action action = actions.get(request.getMethod() + request.getPathInfo());
        if (action == null) {
            return actions.get("GET/error");
        }
        return action;
    }
}
