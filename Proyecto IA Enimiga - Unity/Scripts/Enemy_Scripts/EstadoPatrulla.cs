using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EstadoPatrulla : MonoBehaviour
{
    public Transform[] WayPoint;
    SpriteRenderer spriteRenderer;
    Rigidbody2D rigidBody;
    Animator animator;

    private ControllerNavMesh controladorNavMesh;
    private int sgtWayPoint;
    private EnemyController maquinaEstados;
    private ControllerVision controllerVision;

    private NavMeshAgent navMeshAgent;


    void Awake()
    {
        rigidBody = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
        controladorNavMesh = GetComponent<ControllerNavMesh>();
        navMeshAgent = GetComponent<NavMeshAgent>();
        maquinaEstados = GetComponent<EnemyController>();
        controllerVision = GetComponent<ControllerVision>();

        navMeshAgent.updateRotation = false;
        navMeshAgent.updateUpAxis = false;
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        RaycastHit2D hit;

        if (controllerVision.VerJugador(out hit))
        {
            controladorNavMesh.perseguirObjectivo = hit.transform;
            animator.SetBool("isAlert", false);
            maquinaEstados.ActivarEstado(maquinaEstados.EstadoPersecucion);
            return;

        }

        if (controladorNavMesh.Llegada())
        {
            sgtWayPoint = (sgtWayPoint  + 1) % WayPoint.Length;
            ActualizarWayPoint();
        }
        animator.SetBool("isMoving", true);

        if(maquinaEstados.GetDamage() <= 0)
        {
            maquinaEstados.ActivarEstado(maquinaEstados.EstadoMuerte);
            return;
        }
    }

    private void OnEnable()
    {
        ActualizarWayPoint();
    }

    void ActualizarWayPoint()
    {

        controladorNavMesh.ActualizarPuntoDestinoNavMeshAgent(WayPoint[sgtWayPoint].position);
        if (transform.position.x < WayPoint[sgtWayPoint].position.x)
        {
            transform.eulerAngles = new Vector3(0, 0, 0);
        }
        else if (transform.position.x > WayPoint[sgtWayPoint].position.x)
        {
            transform.eulerAngles = new Vector3(0, 180, 0);
        }
    }


    public void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.CompareTag("Player") && enabled)
        {
            animator.SetBool("isAlert", true);
            maquinaEstados.ActivarEstado(maquinaEstados.EstadoAlerta);
        }
    }

}
