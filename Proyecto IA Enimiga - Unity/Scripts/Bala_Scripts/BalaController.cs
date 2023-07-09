using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BalaController : MonoBehaviour
{
    [SerializeField] private float speed;

    [SerializeField] private float damage;

    [SerializeField] private float timeOfLife;

    private void Start()
    {
        Destroy(gameObject, timeOfLife);
    }

    private void Update()
    {
        transform.Translate(Vector2.right * speed * Time.deltaTime);
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.CompareTag("Enemy"))
        {
            collision.GetComponent<EnemyController>().GetDamage(damage);
            Destroy(gameObject);
        }

        if (collision.gameObject.layer == LayerMask.NameToLayer("obstaculos"))
        {
            Destroy(gameObject);
        }
    }

}
