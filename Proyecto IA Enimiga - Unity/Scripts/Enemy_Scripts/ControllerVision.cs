using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ControllerVision : MonoBehaviour
{
    public Transform ojos;
    public float rangoVision;
    public float rangoAtaque;
    public LayerMask targetLayer;

    private ControllerNavMesh controllerNavMesh;

    private void Awake()
    {
        controllerNavMesh = GetComponent<ControllerNavMesh>();
    }

    private void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, rangoVision);
        Gizmos.DrawWireSphere(transform.position, rangoAtaque);
    }

    public bool VerJugador(out RaycastHit2D hitt)
    {


        Collider2D[] targets = Physics2D.OverlapCircleAll(ojos.position, rangoVision, targetLayer);



        foreach (Collider2D target in targets)
        {
            // El objeto detectado está en el layer deseado
            if (target.gameObject.CompareTag("Player"))
            {
                Debug.Log("Entro");
                //Verificar si el jugador está a la vista sin obstáculos
                Vector2 direccion = (target.transform.position - ojos.position).normalized;
                float distancia = Vector2.Distance(ojos.position, target.transform.position);


                // Comprobar si alguno de los rayos choca con un objeto de obstáculo
                RaycastHit2D[] Hits = Physics2D.RaycastAll(ojos.position, direccion, distancia, targetLayer);

                foreach (RaycastHit2D raycastHit in Hits)
                {
                    if (raycastHit.rigidbody.CompareTag("Player"))
                    {
                        hitt = raycastHit;
                        Debug.DrawLine(ojos.position, hitt.point, Color.green);
                        Debug.Log("Vio");
                        return true;
                    }
                    Debug.Log("Choca con obstaculo");
                    break;
                }



            }
        }

        hitt = default;
        return false;




    }

    public bool RangoAtaque()
    {
        Collider2D[] targets = Physics2D.OverlapCircleAll(transform.position, rangoAtaque, targetLayer);

        foreach (Collider2D target in targets) {
            if (target.CompareTag("Player"))
            {
                return true;
            }
        }
        return false;
    }
}


