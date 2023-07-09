using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyController : MonoBehaviour
{
    [SerializeField] private float life = 100;

    public MonoBehaviour EstadoPatrulla;
    public MonoBehaviour EstadoAlerta;
    public MonoBehaviour EstadoPersecucion;
    public MonoBehaviour EstadoInicial;
    public MonoBehaviour EstadoAtaque;
    public MonoBehaviour EstadoMuerte;

    private MonoBehaviour estadoActual;
    public Animator animator;
    private void Start()
    {
        ActivarEstado(EstadoInicial);
        animator = GetComponent<Animator>();
    }

    public void ActivarEstado(MonoBehaviour nuevoEstado)
    {
        if(estadoActual != null)
        {
            estadoActual.enabled = false;
        }
        estadoActual = nuevoEstado;
        estadoActual.enabled = true;
    }

    public void GetDamage(float damage)
    {
        life -= damage;
        animator.SetTrigger("isHit");
    }

    public float GetDamage()
    {
        return life;
    }
}
