package cl.duoc.milsabores.service;

import cl.duoc.milsabores.model.Rol;
import cl.duoc.milsabores.model.Usuario;
import cl.duoc.milsabores.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        if (usuario.getRol() == null) {
            usuario.setRol(Rol.CLIENTE);
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String correo, String password) {
        return usuarioRepository.findByCorreo(correo)
                .filter(u -> u.getPassword().equals(password));
    }

    public Optional<Usuario> actualizar(Long id, Usuario detalles) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(detalles.getNombre());
            usuario.setCorreo(detalles.getCorreo());
            if (detalles.getPassword() != null && !detalles.getPassword().isEmpty()) {
                usuario.setPassword(detalles.getPassword());
            }
            if (detalles.getRol() != null) {
                usuario.setRol(detalles.getRol());
            }
            return usuarioRepository.save(usuario);
        });
    }

    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}