import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {
    // Cambia los valores si tu base, usuario o contraseña son diferentes
    private static final String URL = "jdbc:postgresql://localhost:5432/registro_usuarios";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin"; 

    public static boolean insertarUsuario(String nombre, String apellido_Paterno, String apellido_Materno, String correo, String contrasena) {
        String sql = "INSERT INTO usuarios (nombre, apellido_paterno, apellido_materno, correo, contrasena) VALUES (?, ?, ?, ?, ?)";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido_Paterno);
            ps.setString(3, apellido_Materno);
            ps.setString(4, correo);
            ps.setString(5, contrasena);

            int filas = ps.executeUpdate();
            con.close();

            return filas > 0;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
}
