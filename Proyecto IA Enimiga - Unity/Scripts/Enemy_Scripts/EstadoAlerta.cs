using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EstadoAlerta : MonoBehaviour
{
    public float duracionBusqueda = 4f;
    private float tiempoBuscando;
    private bool isRotatingRight = true;
    private float elapsedTime = 0f;

    Rigidbody2D rigidBody;
    Animator animator;
    private EnemyController maquinaEstado;
    private ControllerNavMesh controladorNavMesh;
    SpriteRenderer spriteRenderer;
    private ControllerVision controllerVision;

    private NavMeshAgent navMeshAgent;
    void Awake()
    {
        rigidBody = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        maquinaEstado = GetComponent<EnemyController>();
        controladorNavMesh = GetComponent<ControllerNavMesh>();
        spriteRenderer = GetComponent<SpriteRenderer>();
        controllerVision = GetComponent<ControllerVision>();
    }

    private void OnEnable()
    {
        controladorNavMesh.DetenerNevMeshAgent();
        tiempoBuscando = 0f;
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        RaycastHit2D hit;

        if (controllerVision.VerJugador(out hit))
        {
            controladorNavMesh.perseguirObjectivo = hit.transform;
            animator.SetBool("isAlert", false);
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoPersecucion);
            return;

        }

        animator.SetBool("isAlert", true);
        // Calcular el ángulo de rotación en función del estado actual
        float angle = isRotatingRight ? 0f : 180f;

        // Aplicar la rotación al objeto
        transform.eulerAngles = new Vector3(0, angle, 0);

        elapsedTime += Time.deltaTime;

        // Alternar el estado de rotación después de 0.5 segundos
        if (elapsedTime >= 0.8f)
        {
            isRotatingRight = !isRotatingRight;
            elapsedTime = 0f;
        }

        tiempoBuscando += Time.deltaTime;

        if(tiempoBuscando >= duracionBusqueda)
        {
            animator.SetBool("isAlert", false);
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoPatrulla);
            return;
        }

        if (maquinaEstado.GetDamage() <= 0)
        {
            maquinaEstado.ActivarEstado(maquinaEstado.EstadoMuerte);
        }

    }
}
