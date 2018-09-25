#-*-coding:gbk-*-
'''
��򵥵�ѵ��ģ�ͣ���ȷ�ʴ�Լ91%
'''
import tensorflow as tf
from tensorflow.contrib.learn.python.learn.datasets.mnist import read_data_sets

mnist = read_data_sets('MNIST_data', one_hot=True) #When connect fail, can create an MNIST_data directory and put files manually

x = tf.placeholder(tf.float32, [None,784]) #����x����һ��ռλ��
W = tf.Variable(tf.zeros([784,10])) #W����ʼ��Ϊ0
b = tf.Variable(tf.zeros([10])) #b����ʼ��Ϊ0
y = tf.nn.softmax(tf.matmul(x,W) + b) #����ģ�ͣ�xW���+b�������뵽tf.nn.softmax������
y_ = tf.placeholder("float", [None,10]) #y_ʵ�ʷֲ�
cross_entropy = -tf.reduce_sum(y_*tf.log(y)) #�����أ�Ҳ������ʧ

train_step = tf.train.GradientDescentOptimizer(0.01).minimize(cross_entropy) #ѵ��ģ��
init = tf.global_variables_initializer()

sess = tf.Session()
sess.run(init)

for i in range(1000):
    batch_xs, batch_ys = mnist.train.next_batch(100)
    sess.run(train_step, feed_dict={x: batch_xs, y_: batch_ys})
    
correct_prediction = tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
print(sess.run(accuracy, feed_dict={x: mnist.test.images, y_: mnist.test.labels}))