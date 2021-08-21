package edu.demian.wp.web.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("/home", new HomeAction());
        actions.put("/error", new ErrorAction());
        actions.put("/login", new LoginAction());
        actions.put("/register", new RegisterAction());
        actions.put("/logout", new LogoutAction());
        actions.put("/expos", new ExposAction());
        actions.put("/moderator/addExpo", new AddExpoAction());
        actions.put("/moderator/deleteExpo", new DeleteExpoAction());
        actions.put("/moderator/updateExpo", new UpdateExpoAction());
        actions.put("/search", new SearchAction());
    }

    public static Action getAction(HttpServletRequest request) {
        Action action = actions.get(request.getPathInfo());
        if (action == null) {
            return actions.get("/error");
        }
        return action;
    }
}
