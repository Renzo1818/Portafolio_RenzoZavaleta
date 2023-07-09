using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.InputSystem;

public class PlayerController : MonoBehaviour
{
    [SerializeField] private float life;

    [SerializeField] private float maxHealth;

    [SerializeField] private VidaController healthBar;

    public bool recibeGolpe;

    public int empuje;

    public float moveSpeed = 1f;

    public ContactFilter2D contactFilter;

    public float collisionOffset = 0.5f;

    Vector2 movementInput;

    SpriteRenderer spriteRenderer;

    Rigidbody2D rigidBody;

    public Animator animator;

    List<RaycastHit2D> castCollisions = new List<RaycastHit2D>();

    bool canMove = true;

    // Start is called before the first frame update
    void Start()
    {
        rigidBody = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();

        life = maxHealth;
        healthBar.CreateHealthBar(life);
    }

    private void FixedUpdate()
    {
        if(!recibeGolpe) 
        {
            if (canMove)
            {
                //Si el input del movimiento no es cero, entonces tratará de moverse 
                if (movementInput != Vector2.zero)
                {
                    bool success = TryMove(movementInput);

                    if (!success)
                    {
                        success = TryMove(new Vector2(movementInput.x, 0));
                    }

                    if (!success)
                    {
                        success = TryMove(new Vector2(0, movementInput.y));
                    }

                    animator.SetBool("isMoving", success);
                }
                else
                {
                    animator.SetBool("isMoving", false);
                }

                //Definir la dirección del sprite dependiendo de la dirección del movimiento (movementInput)
                if (movementInput.x < 0)
                {
                    transform.eulerAngles = new Vector3(0, 180, 0);
                }
                else if (movementInput.x > 0)
                {
                    transform.eulerAngles = new Vector3(0, 0, 0);
                }

            }
        }
    }

    private bool TryMove(Vector2 direction)
    {
        if (direction != Vector2.zero)
        {
            //Revisa potenciales colisiones
            int count = rigidBody.Cast(
                    direction,
                    contactFilter,
                    castCollisions,
                    moveSpeed * Time.fixedDeltaTime + collisionOffset
                    );

            if (count == 0)
            {
                rigidBody.MovePosition(rigidBody.position + direction * moveSpeed * Time.fixedDeltaTime);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            //No se puede mover, entonces no haber ninguna dirección de movimiento 
            return false;
        }
    }

    void OnMove(InputValue movementValue)
    {
        movementInput = movementValue.Get<Vector2>();
    }

    //Bloquear movimiento mientres te encuentres en el estado Ataque ==> eventos dentro de la animación 
    public void LockMovement()
    {
        canMove = false;
    }

    public void UnlockMovement()
    {
        canMove = true;
    }

    public void Damage()
    {
        if (recibeGolpe)
        {
            Vector2 movement = Vector2.right * empuje;

            // Realizar un raycast para detectar colisiones con paredes
            RaycastHit2D hit = Physics2D.Raycast(transform.position, movement, collisionOffset, LayerMask.GetMask("obstaculos"));
            if (hit.collider != null)
            {
                // Ajustar la posición del jugador para evitar quedar atrapado
                rigidBody.MovePosition(hit.point - movement * collisionOffset);
            }
            else
            {
                // Mover al jugador en la dirección del empuje
                rigidBody.MovePosition(rigidBody.position + movement * Time.deltaTime);
            }
        }
    }

    public void FinishDamage()
    {
        canMove = true;
        recibeGolpe = false;
    }

    public void TakeDamage(float damage)
    {
        life -= damage;

        healthBar.ChangeCurrentHealth(life);
        
        animator.SetTrigger("isDamage");

        if (life <= 0)
        {
            animator.SetTrigger("isDead");
        }
    }

    public void Cure(float healing)
    {
        if ((life + healing) > maxHealth)
        {
            life = maxHealth;
        }
        else 
        {
            life += healing;
        }
    }
}
