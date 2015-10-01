package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExceptionMappers {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    static ServletContext context;
    
    public static Response ExceptionToJson(PersonNotFoundException ex){
        boolean isDebug;
        int code = ex.getCode();
        if(context == null)
            isDebug = false;
        else
            isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(ex, code, isDebug);
        return Response.status(code)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }
}
