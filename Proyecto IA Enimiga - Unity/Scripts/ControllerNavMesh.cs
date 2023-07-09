using UnityEngine;
using UnityEngine.AI;

public class ControllerNavMesh : MonoBehaviour
{
    [HideInInspector]public Transform perseguirObjectivo;
    private NavMeshAgent navMeshAgent;

    // Start is called before the first frame update
    void Awake()
    {
        navMeshAgent = GetComponent<NavMeshAgent>();
    }

    // Update is called once per frame
    public void ActualizarPuntoDestinoNavMeshAgent(Vector3 puntoDestino)
    {
        navMeshAgent.destination = puntoDestino;
        navMeshAgent.isStopped = false;
    }

    public void ActualizarPuntoDestinoNavMeshAgent()
    {
        ActualizarPuntoDestinoNavMeshAgent(perseguirObjectivo.position);
    }

    public void DetenerNevMeshAgent()
    {
        navMeshAgent.isStopped = true;
    }

    public bool Llegada()
    {
        return navMeshAgent.remainingDistance <= navMeshAgent.stoppingDistance && !navMeshAgent.pathPending;
    }
}
