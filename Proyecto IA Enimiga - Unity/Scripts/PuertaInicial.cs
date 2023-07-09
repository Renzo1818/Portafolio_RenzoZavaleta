using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PuertaInicial : MonoBehaviour
{
    private ScoreController scoreController;
    private int score;
    public Transform jugador;
    public float distanciaMinima = 0.2f;
    public BoxCollider2D colliderPuerta;
    public Transform tp;

    private void Awake()
    {
        scoreController = FindObjectOfType<ScoreController>();
        colliderPuerta = GetComponent<BoxCollider2D>();

        if (scoreController == null)
        {
            Debug.LogError("No se encontró un objeto ScoreController en la escena.");
        }
    }

    public void Update()
    {
        if (scoreController == null)
            return;

        score = scoreController.valor();
        float distancia = Vector2.Distance(transform.position, jugador.position);

        if (score == 2)
        {
            colliderPuerta.isTrigger = true;
            if (distancia <= distanciaMinima)
            {
                jugador.gameObject.transform.position = tp.position;
            }
        }

    }
}
