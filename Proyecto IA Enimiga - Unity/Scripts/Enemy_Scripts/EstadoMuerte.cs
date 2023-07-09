using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EstadoMuerte : MonoBehaviour
{
    private NavMeshAgent navMeshAgent;

    Animator animator;

    void Start()
    {
        animator = GetComponent<Animator>();
        navMeshAgent = GetComponent<NavMeshAgent>();
        OnDead();

    }

    public void OnDead()
    {
        navMeshAgent.isStopped = true;
        animator.SetTrigger("isDead");
        Invoke("DestroyObject", 1f);
    }

    private void DestroyObject()
    {
        Destroy(animator.gameObject);
        Destroy(gameObject);
    }

}
