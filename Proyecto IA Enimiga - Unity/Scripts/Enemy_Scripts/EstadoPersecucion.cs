using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EstadoPersecucion : MonoBehaviour
{
    private EnemyController maquinaEstado;
    private ControllerNavMesh navMesh;
    private ControllerVision controladorVision;
    private Animator animator;
    SpriteRenderer spriteRenderer;
    void Awake()
    {
        maquinaEstado = GetComponent<EnemyController>();
        navMesh = GetComponent<ControllerNavMesh>();
        controladorVision = GetComponent<ControllerVision>();
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
    }
    void FixedUpdate()
    {
        RaycastHit2D hit;
        if (!controladorVision.VerJugador(out hit))
        {
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoAlerta);
            return;
        }
        if (controladorVision.RangoAtaque())
        {
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoAtaque);
            animator.SetBool("isAttack", true);
            return;
        }

        navMesh.ActualizarPuntoDestinoNavMeshAgent();
        if (transform.position.x < navMesh.perseguirObjectivo.position.x)
        {
            transform.eulerAngles = new Vector3(0, 0, 0);
        }
        else if (transform.position.x > navMesh.perseguirObjectivo.position.x)
        {
            transform.eulerAngles = new Vector3(0, 180, 0);
        }
        if (maquinaEstado.GetDamage() <= 0)
        {
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoMuerte);
        }
    }
}
