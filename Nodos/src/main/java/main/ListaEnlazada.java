package main;

/**
 *Nombre: Sebastian Garcia
 */

//Clase Lista Enlazada
public class ListaEnlazada {
  //Declaración de variable
  Nodo cabeza;

  //Metodo para añadir datos
  public void add(int x) {
    Nodo nuevo = new Nodo(x);
    if(cabeza == null) {
      cabeza = nuevo;
      return;
    }
    Nodo actual = cabeza;
    while(actual.siguiente != null) {
      actual = actual.siguiente; 
    }
    actual.siguiente = nuevo;
  }

  //Metodo para eliminar datos
  public void removerDuplicados() {
    if(cabeza == null) return;
    
    Nodo actual = cabeza;
    while(actual != null && actual.siguiente != null) {
      if(actual.valor == actual.siguiente.valor) {
        actual.siguiente = actual.siguiente.siguiente;
      }
      else {
        actual = actual.siguiente;
      }
    }
  }

  //Metodo para imprimir
  public void print() {
      
    Nodo tmp = cabeza;
    while(tmp != null) {
      System.out.print(tmp.valor + " ");
      tmp = tmp.siguiente;
    }
    System.out.println();
  }
    
  }
  
  
  

