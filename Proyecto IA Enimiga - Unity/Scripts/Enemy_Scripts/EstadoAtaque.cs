using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EstadoAtaque : MonoBehaviour
{
    private ControllerVision controladorVision;
    private Animator animator;
    private EnemyController maquinaEstado;

    public Transform controladorGolpe;
    public float radioGolpe;
    public float damageHit;
    public float tiempoEntreAtaque;
    public float tiempoSiguienteAtaque;    
    void Awake()
    {
        controladorVision = GetComponent<ControllerVision>();
        animator = GetComponent<Animator>();
        maquinaEstado = GetComponent<EnemyController>();
    }

    // Update is called once per frame
    void Update()
    {
        if (!controladorVision.RangoAtaque())
        {
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoPersecucion);
            animator.SetBool("isAttack", false);
            return;
        }

        if(tiempoEntreAtaque > 0)
        {
            tiempoSiguienteAtaque -= Time.deltaTime;
        }

        Collider2D[] objetos = Physics2D.OverlapCircleAll(controladorGolpe.position, radioGolpe);
        foreach(Collider2D colisionadores in objetos)
        {
            if (colisionadores.CompareTag("Player"))
            {
                if(tiempoSiguienteAtaque <= 0)
                {
                    if (transform.position.x > colisionadores.transform.position.x)
                    {
                        colisionadores.GetComponent<PlayerController>().empuje = -50;
                        colisionadores.transform.rotation = Quaternion.Euler(0, 0, 0);
                    }
                    else
                    {
                        colisionadores.GetComponent<PlayerController>().empuje = 50;
                        colisionadores.transform.rotation = Quaternion.Euler(0, 180, 0);
                    }

                    colisionadores.transform.GetComponent<PlayerController>().recibeGolpe = true;
                    colisionadores.transform.GetComponent<PlayerController>().TakeDamage(damageHit);
                    tiempoSiguienteAtaque = tiempoEntreAtaque;
                }

            }
        }

        if (maquinaEstado.GetDamage() <= 0)
        {
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoMuerte);
        }

    }


    private void OnDrawGizmos()
    {
        Gizmos.color = Color.green;
        Gizmos.DrawWireSphere(controladorGolpe.position, radioGolpe);
    }

}
