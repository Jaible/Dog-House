const Confirm = {
    open(options) {
        options = Object.assign({}, {
            title: '',
            mensaje: '',
            okText: 'Aceptar',
            cancelText: 'Cancelar',
            onok: function () {},
            oncancel: function () {}
        }, options);

        const html = `
            <div class="confirm">
                    <div class="confirm__window">
                        <div class="confirm__titlebar">
                            <span class="confirm__title">${options.title}</span>
                            <button class="confirm__close">&times;</button>
                        </div>
                        
                        <div class="confirm__content">${options.mensaje}</div>
                        
                        <div class="confirm__buttons">
                            <button class="confirm__button confirm__button--ok confirm__button--fill" href="/Pagos">${options.okText}</button>
                            <button class="confirm__button confirm__button--cancel">${options.cancelText}</button>
                        </div>
                    </div>
            </div>
        `;
        
        const template = document.createElement('template');
        template.innerHTML = html;
        
        const confirmE1 = template.content.querySelector('.confirm');
        const btnClose = template.content.querySelector('.confirm__button');
        const btnOk = template.content.querySelector('.confirm__button--ok');
        const btnCancel = template.content.querySelector('.confirm__button--cancel');
        
        confirmE1.addEventListener('click', e => {
            if(e.target === confirmE1){
                options.oncancel();
                this._close(confirmE1);
            }
        });
        
        btnOk.addEventListener('click', () => {
            options.onok();
            this._close(confirmE1);
            var url = window.location.href;
            window.location.href = url+"/Pagos";
        });
        
        [btnClose, btnCancel].forEach(el => {
            el.addEventListener('click', () => {
                options.oncancel();
                this._close(confirmE1);
            });
        });
        
        document.body.appendChild(template.content);
    },

    _close(confirmE1) {
        confirmE1.classList.add('confirm--close');
        
        confirmE1.addEventListener('animationend', () => {
            document.body.removeChild(confirmE1);
        });
    }
};