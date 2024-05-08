/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.sga.models;

import java.io.Serializable;
import java.security.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Cristian Fierro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Records<T> implements Serializable {
    private Timestamp dateCreated;
    private Timestamp dateUpdate;
    private T createUser;
    private T updateUser;
}
