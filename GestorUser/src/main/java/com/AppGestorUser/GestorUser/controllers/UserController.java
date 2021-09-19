package com.AppGestorUser.GestorUser.controllers;


import com.AppGestorUser.GestorUser.dao.IUserDao;
import com.AppGestorUser.GestorUser.models.Usuario;
import com.AppGestorUser.GestorUser.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping("/user/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        return userDao.getUserById(id);
    }

    @RequestMapping("/users")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        if (!validateToken(token)){
            return null;
        }
        return userDao.getUsers();
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value="Authorization") String token,
                       @PathVariable Long id){
        if (!validateToken(token)){
            return;
        }
        userDao.deleteUserById(id);
    }

    @PostMapping("/users")
    public void registerUser(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        userDao.register(usuario);
    }

    private boolean validateToken(String token){
        String userId = jwtUtil.getKey(token);
        return  userId != null;
    }

}
