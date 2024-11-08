package experiment.TCP;

import java.io.ObjectOutputStream;

public class ServerSideUser {

    ObjectOutputStream oos;
    String nick;

    public ServerSideUser(ObjectOutputStream oos, String nick) {
        this.oos = oos;
        this.nick = nick;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
